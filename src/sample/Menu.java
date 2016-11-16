package sample;

import java.util.ArrayList;

/**
 * Created by Adam on 4/9/16.
 */
public class Menu {
    ArrayList<String> menu = new ArrayList<>();
    int difficulty = 0;
    int mines = 0;
    int months = 0;
    double expenses;
    double salaries = 0;
    double income = 0;
    double profit = 0;
    double funds = 0;
    double lifetimeMoney = 0;

    public Menu(SpaceStation current){
        update(current);
    }

    public String update(SpaceStation current){
        this.difficulty = current.getDifficulty();
        this.mines = current.getModules();
        this.months = current.getMonths();
        this.expenses = current.getExpenses();
        this.salaries = current.getSalaries();
        this.income = current.getIncome();
        this.profit = current.getProfit();
        this.funds = current.getFunds();
        this.lifetimeMoney = current.getLifetimeMoney();
        createMenu(current);
        return menu.get(0);
    }

    public void createMenu(SpaceStation current){
        String mainMenu = "*Main Menu* \n\nModule management \nFinance \n\nNext month";
        this.menu.add(mainMenu);//menu 0 main
        StringBuilder mineList = new StringBuilder();
        mineList.append("module \tfunction \tcrew \tefficiency");
        mineList.append(System.lineSeparator());
        mineList.append("Select a module.");
        mineList.append(System.lineSeparator());
        for (int i = 0; i < this.mines; i++){
            mineList.append("module " + (i+1) + "\t");
            mineList.append((current.operationalModules.get(i).getFunction()));
            mineList.append("\t");
            mineList.append(current.operationalModules.get(i).getCrew());
            mineList.append("\t\t");
            mineList.append((current.operationalModules.get(i).getEfficiency() * 100) + "%");
            mineList.append(System.lineSeparator());
        }
        mineList.append(System.lineSeparator());
        mineList.append("Total crew\t");
        mineList.append(current.getTotalCrew());
        mineList.append(System.lineSeparator());
        mineList.append("Build Module");
        mineList.append(System.lineSeparator());
        mineList.append(System.lineSeparator());
        mineList.append("Back");
        this.menu.add(mineList.toString()); // menu 1 mine list
        StringBuilder finance = new StringBuilder();
        finance.append("Income \t\t$" + this.income);
        finance.append(System.lineSeparator());
        finance.append("Expenses \t$" + expenses);
        finance.append(System.lineSeparator());
        finance.append("Salaries \t$" + salaries);
        finance.append(System.lineSeparator());
        finance.append("Funds \t\t$" + funds);
        finance.append(System.lineSeparator());
        finance.append(System.lineSeparator());
        finance.append("Back");
        this.menu.add(finance.toString());//menu 2 finance
        StringBuilder managemine = new StringBuilder();
        managemine.append("Hire");
        managemine.append(System.lineSeparator());
        managemine.append("$200");
        managemine.append(System.lineSeparator());
        managemine.append(System.lineSeparator());
        managemine.append("Fire");
        managemine.append(System.lineSeparator());
        managemine.append(System.lineSeparator());
        managemine.append("Destroy");
        managemine.append(System.lineSeparator());
        managemine.append(System.lineSeparator());
        managemine.append("Back");
        this.menu.add(managemine.toString());//menu 3 manage individual mines
        StringBuilder mineChooser = new StringBuilder();
        mineChooser.append("Please choose a module to build");
        mineChooser.append(System.lineSeparator());
        mineChooser.append("turret");
        mineChooser.append(System.lineSeparator());
        mineChooser.append("research");
        mineChooser.append(System.lineSeparator());
        mineChooser.append("dock");
        mineChooser.append(System.lineSeparator());
        mineChooser.append("living");
        mineChooser.append(System.lineSeparator());
        mineChooser.append(System.lineSeparator());
        mineChooser.append("Back");
        this.menu.add(mineChooser.toString());// menu 4 choosing a mine;
        this.menu.add("How many crew members would you like to hire? \n\nBack");// menu 5 choosing # of crew to hire
        this.menu.add("How many crew members would you like to fire? \n\nBack");// menu 6 choosing # of crew to fire
    }
}
