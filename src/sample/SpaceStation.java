package sample;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Adam on 4/9/16.
 */
public class SpaceStation {
    ArrayList<Modules> operationalModules = new ArrayList<>();
    int difficulty = 0;
    int modules = 0;
    int months = 0;
    int totalCrew = 0;
    int percent = 0;
    int currentMenu = 0;
    int selectedModule = 0;
    double expenses;
    double salaries = 0;
    double income = 0;
    double profit = 0;
    double funds = 0;
    double lifetimeMoney = 0;
    boolean newGame = true;
    boolean managing = true;

    public SpaceStation(String input){
        if (input.toLowerCase().contains("easy")) {
            difficulty = 3;
        } else if (input.toLowerCase().contains("medium")){
            difficulty = 2;
        } else if (input.toLowerCase().contains("hard")){
            difficulty = 1;
        }
        if (difficulty > 0) {
            setDifficulty(difficulty);
            createShafts(modules);
            newGame = false;
            setExpenses();
        }
    }

    public String processInput(String input, Menu menu){
        if (input.toLowerCase().equals("quit")){
            setMinesToZero();
            this.managing = false;
            return "Thanks for playing! \nYou ran your station for " + getMonths() + " months. \nYour total earnings were $" + getLifetimeMoney() + ".";
        } else if (input.contains("1776")){
            return freedom();
        } else if (input.equals("")){
            return menu.menu.get(currentMenu);
        } else if (input.toLowerCase().contains("module management") && currentMenu != 1){
            this.currentMenu = 1;
            return menu.menu.get(1);
        } else if (input.toLowerCase().trim().equals("finance") && currentMenu != 2){
            this.currentMenu = 2;
            return menu.menu.get(2);
        } else if (input.toLowerCase().trim().equals("back")){
            if (this.currentMenu == 1 || this.currentMenu == 2){
                this.currentMenu = 0;
                return menu.menu.get(0);
            } else if (this.currentMenu == 3){
                this.currentMenu = 1;
                return menu.menu.get(1);
            } else if (this.currentMenu == 5 || this.currentMenu == 6){
                this.currentMenu = 1;
                return menu.menu.get(1);
            }
        } else if (input.toLowerCase().contains("module") && currentMenu != 3){
            this.currentMenu = 3;
            String justNumber = input.substring(input.indexOf("e")+1);
            String mineNumber = justNumber.trim();
            if (mineNumber.length() > 0 && mineNumber.length() < 3) {
                this.selectedModule = Integer.parseInt(mineNumber);
                return menu.menu.get(3);
            }
        } else if (input.toLowerCase().trim().equals("hire") && currentMenu != 5){
            this.currentMenu = 5;
            return menu.menu.get(5);
        } else if (input.toLowerCase().trim().equals("fire") && currentMenu != 6){
            this.currentMenu = 6;
            return menu.menu.get(6);
        } else if (input.toLowerCase().trim().equals("build")){
            currentMenu = 4;
            return menu.menu.get(4);
        } else if (currentMenu == 5){
            int hired = Integer.parseInt(input);
            return hireWorkers(selectedModule -1, hired);
        } else if (currentMenu == 6){
            int fired = Integer.parseInt(input);
            return fireWorkers(selectedModule -1, fired);
        } else if (currentMenu == 4){
            int type = 0;
            if (input.toLowerCase().trim().equals("turret")){
                type = 0;
            } else if (input.toLowerCase().trim().equals("research stattion")){
                type = 1;
            } else if (input.toLowerCase().trim().equals("dock")){
                type = 2;
            } else if (input.toLowerCase().trim().equals("living quarters")){
                type = 3;
            }
            buildMine(type);
            operationalModules.add(new Modules(type));
            this.modules++;
            this.income += operationalModules.get(modules).getIncomeGenerated();
            this.totalCrew += operationalModules.get(modules).getCrew();
        } else if (input.toLowerCase().trim().equals("next month")){
            this.currentMenu = 0;
            this.managing = false;
            return nextMonth();
        }
        return "please put in a proper input.";
    }

    public void update(){
        this.income = 0;
        this.totalCrew = 0;
        this.profit = 0;
        for (int i = 0; i < modules; i++) {
            this.income += operationalModules.get(i).getIncomeGenerated();
            this.totalCrew += operationalModules.get(i).getCrew();
        }
        setSalaries();
        this.expenses = salaries;
        this.profit = income - expenses;
    }

    public void setDifficulty(int difficulty){
        this.percent = difficulty * 10;
        this.modules = difficulty * 2;
        this.funds = difficulty * 1000;
        this.lifetimeMoney = funds;
    }

    public void createShafts(int mines){
        Random r = new Random();
        for (int i = 0; i < mines; i++) {
            operationalModules.add(new Modules(r.nextInt(4)));
            this.income += operationalModules.get(0).getIncomeGenerated();
            this.totalCrew += operationalModules.get(i).getCrew();
        }
    }

    public void changeDifficulty(int difficulty){
        this.percent = difficulty * 10;
    }

    public void buildMine(int type){
        double minePrice = getPrice(type);//price of mines and deduction, like the hire and fire methods
        if (this.funds > minePrice){
            operationalModules.add(new Modules(type));
            this.modules++;
            this.income += operationalModules.get(modules).getIncomeGenerated();
            this.totalCrew += operationalModules.get(modules).getCrew();
        }

    }

    public void setManagingTrue(){
        this.managing = true;
    }

    public void setIncome(){
        this.income = 0;
        for (int i = 0; i < modules; i++) {
            this.income += operationalModules.get(i).getIncomeGenerated();
            this.totalCrew += operationalModules.get(i).getCrew();
        }
        this.profit = this.income - this.expenses;
    }

    public void setExpenses(){
        setSalaries();
        this.expenses = salaries;
    }

    public void setSalaries(){
        double totalSalaries = 0;
        for (int i = 0; i < this.modules; i++){
            totalSalaries += operationalModules.get(i).getSalaries();
        }
        this.salaries = totalSalaries;
    }

    public void setMinesToZero(){
        this.modules = 0;
    }

    public String hireWorkers(int mineChosen, int hired){
        if ((hired*200) < funds) {
            operationalModules.get(mineChosen).hireWorkers(hired);
            operationalModules.get(mineChosen).update();
            this.funds -= (hired*200);
            this.totalCrew += hired;
            update();
            return "You hired " + hired + " crew.";
        } else{
            return "you don't have enough funds to hire those crew.";
        }
    }

    public String fireWorkers(int mineChosen, int fired){
        if (fired < operationalModules.get(mineChosen).getCrew()) {
            operationalModules.get(mineChosen).fireWorkers(fired);
            operationalModules.get(mineChosen).update();
            this.totalCrew -= fired;
            update();
            return "You fired " + fired + " crew";
        }else{
            return "You don't have that many crew members at that module.";
        }
    }

    public String nextMonth(){
        StringBuilder sb =  new StringBuilder();
        int i = 0;
        int disasters = 3;
        this.profit = income - salaries;
        this.funds += profit;
        this.lifetimeMoney += this.profit;
        this.months++;
        while (disasters >= difficulty && i < modules) {
            int workerdiffcheck = operationalModules.get(i).getCrew();
            operationalModules.get(i).disasterChance(this.percent, operationalModules.get(i).getType());
            if (workerdiffcheck != operationalModules.get(i).getCrew()){
                disasters--;
                sb.append("\n\n" + operationalModules.get(i).getDisaster() + " in mine " + (i+1));
            }
            i++;
        }
        for (i = 0; i < modules; i++)
            if (operationalModules.get(i).getCrew() < 1){
                operationalModules.remove(i);
                this.modules --;
                sb.append("\nmodule " + (i+1) + " has been destroyed.");
                i--;
            }
        sb.append("\nYou made $" + profit + " last month.");
        update();
        return sb.toString();
    }

    public String freedom(){
        return "Suh dood";
    }

    public int getModules(){
        return modules;
    }

    public int getDifficulty(){
        return difficulty;
    }

    public int getTotalCrew() {
        return totalCrew;
    }

    public double getFunds(){
        return funds;
    }

    public double getLifetimeMoney(){
        return lifetimeMoney;
    }

    public double getExpenses(){
        return expenses;
    }

    public double getSalaries(){
        return salaries;
    }

    public double getIncome(){
        return income;
    }

    public double getProfit(){
        return profit;
    }

    public int getMonths(){
        return months;
    }

    public boolean getNewGame(){
        return newGame;
    }

    public boolean getManaging(){
        return managing;
    }

    public double getPrice(int type){
        double price = 0;
        if (type == 0){
            price = 500;
        } else if (type == 1){
            price = 1000;
        } else if (type == 2){
            price = 1500;
        } else if (type == 3){
            price = 2000;
        } else if (type == 4){
            price = 2500;
        } else if (type == 5){
            price = 3000;
        }
        return price;
    }
}
