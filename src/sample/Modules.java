package sample;

import java.util.Random;

/**
 * Created by Adam on 4/9/16.
 */
public class Modules {
    int type = 0;
    int hired = 0;
    int crew = 0;
    double salaries = 100;
    double incomeGenerated = 0;
    double efficiency = 0;
    String function = "";
    String disaster = "";

    public Modules(int type){
        setType(type);
        crew = 1;
        setEfficiency(crew, type);
        setIncomeGenerated(efficiency);
    }

    public void setType(int r){
        if (r == 0){
            function = "turret";
        }else if( r == 1){
            function = "research";
        }else if( r == 2){
            function = "dock";
        }else if( r == 3){
            function = "living";
        }
        type = r;
    }

    public void update(){
        setEfficiency(this.crew, this.type);
        setIncomeGenerated(this.efficiency);
        setSalaries(this.crew);
    }

    public void setEfficiency(int workers, int type){
        double work = workers;
        if (type == 0){
            efficiency = (work/5);
        } else if (type == 1){
            efficiency = (work/8);
        } else if (type == 2){
            efficiency = (work/10);
        } else if (type == 3){
            efficiency = (work/12);
        }
    }

    public void setIncomeGenerated(double efficiency){
        this.incomeGenerated = ((type + 1) * efficiency) * 750;
    }

    public void hireWorkers(int hired){
        crew += hired;
    }

    public void fireWorkers(int fired){
        crew -= fired;
    }

    public void setSalaries(int workers){
        salaries = workers*100;
    }

    public void disasterChance(int percent, int type){
        int chance = (100/percent) + (100/(type+1));
        Random r = new Random();
        int random = r.nextInt(chance+1);
        if ( random == chance){
            disaster(type);
        }
    }

    public String disaster(int type){
        String disasters[] = {"A module caught on fire and 2 crew died.","A meteor hit a module and 5 people died." ,
                "An module depressurised and everyone int the module died.", "A crew member got irradiated and died."};
        Random r = new Random();
        disaster = "";
        int workersLost = 0;
        if (type == 0){
            int bad = r.nextInt(3);
            if (bad == 0){
                disaster = disasters[0];
                workersLost = 2;
            } else if (bad == 1){
                disaster = disasters[2];
                this.crew = 0;
            }
        } else if (type == 1){
            int bad = r.nextInt(3);
            if (bad == 0){
                disaster = disasters[0];
                workersLost = 2;
            } else if (bad == 1){
                disaster = disasters[2];
                this.crew = 0;
            }
        } else if (type == 2){
            int bad = r.nextInt(3);
            if (bad == 0){
                disaster = disasters[0];
                workersLost = 2;
            } else if (bad == 1){
                disaster = disasters[2];
                this.crew = 0;
            }
        } else if (type == 3){
            int bad = r.nextInt(3);
            if (bad == 0){
                disaster = disasters[1];
                workersLost = 5;
            } else if (bad == 0){
                disaster = disasters[0];
                workersLost = 2;
            } else if (bad == 2){
                disaster = disasters[2];
                this.crew = 0;
            }
        }
        this.crew -= workersLost;
        return disaster;
    }

    public double getSalaries(){
        return salaries;
    }

    public int getType(){
        return type;
    }

    public String getFunction(){
        return function;
    }

    public String getDisaster(){
        return disaster;
    }

    public double getIncomeGenerated(){
        return incomeGenerated;
    }

    public int getCrew(){
        return crew;
    }

    public double getEfficiency() {
        return efficiency;
    }
}
