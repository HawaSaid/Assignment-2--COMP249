import java.io.Serializable;

/**
* Hawa-Afnane Said (ID:40263400)
* Haifaa Janoudi (ID:40263748)
* COMP249
* Assignment 2 
* Due date:27/07/2023
 */

public class Team implements Serializable {
    String name;
    String sport;
    int year;
    String score;
    char championship;


    public Team(String name, String sport, int year, String score, char championship) {//Custom constructor
        this.name = name;
        this.sport = sport;
        this.year = year;
        this.score = score;
        this.championship = championship;
    }

    public void setName(String name) {//Setter for the String name
        this.name = name;
    }

    public String getName() {//Getter for the String name
        return name;
    }

    public void setSport(String sport) {//Setter for the String sport
        this.sport = sport;
    }

    public String getSport(String sport) {//Getter for the String sport
        return sport;
    }

    public void setYear(int year) {//Setter for the int year
        this.year = year;
    }

    public int getYear() {//Getter for the int year
        return year;
    }

    public void setScore(String score) {//Setter for the String score
        this.score = score;
    }

    public String getScore() {//Getter for the String score
        return score;
    }

    public void setChampionship(char championship) {//Setter for the character championship
        this.championship = championship;
    }

    public char getChampionship() {//Getter for the character championship
        return championship;
    }

    @Override
    public String toString() {//toString method that overrides the ToString method in the Object class
        return "The name of the team is " + name + ". The sport is " + sport + ". The year the team played is " + year
                + ".The scores were " + score + " and did they win the championship? " + championship;
    }

    @Override
    public boolean equals(Object obj) {//toString method that overrides the equals method in the Object class
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        } else {
            //downcast object obj into a Team object
            Team obj1 = (Team) obj;
            if (name.equals(obj1.name) && sport.equals(obj1.sport) && year == obj1.year && score.equals(obj1.score)
                    && championship == obj1.championship) {
                return true;
            } else {
                return false;
            }
        }
    }

}
