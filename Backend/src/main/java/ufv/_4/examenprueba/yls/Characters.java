package ufv._4.examenprueba.yls;

public class Characters {

    private String id;
    private String name;
    private String ki;
    private String maxKi;
    private String race;
    private String gender;

    public Characters() {

    }

    public Characters(String id, String name, String ki, String maxKi, String race, String gender) {
        this.id = id;
        this.name = name;
        this.ki = ki;
        this.maxKi = maxKi;
        this.race = race;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKi() {
        return ki;
    }

    public void setKi(String ki) {
        this.ki = ki;
    }

    public String getMaxKi() {
        return maxKi;
    }

    public void setMaxKi(String maxKi) {
        this.maxKi = maxKi;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Characters{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ki='" + ki + '\'' +
                ", maxKi='" + maxKi + '\'' +
                ", race='" + race + '\'' +
                ", gender='" + gender + '\'';
    }
}
