package com.example.homework2;

public class Beer {

    private String name;
    private String image;
    private String description;
    private String abv;
    private String first_brewed;
    private String food_pairings;
    private String brewers_tips;

    public Beer(String called, String url, String info, String abv, String first_brewed, String food_pairings, String brewers_tips){
        this.name = called;
        this.image = url;
        this.description = info;
        this.abv = abv;
        this.first_brewed = first_brewed;
        this.food_pairings = food_pairings;
        this.brewers_tips = brewers_tips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getFood_pairings() {
        return food_pairings;
    }

    public void setFood_pairings(String food_pairings) {
        this.food_pairings = food_pairings;
    }

    public String getBrewers_tips() {
        return brewers_tips;
    }

    public void setBrewers_tips(String brewers_tips) {
        this.brewers_tips = brewers_tips;
    }

    public String getFirst_brewed() {
        return first_brewed;
    }

    public void setFirst_brewed(String first_brewed) {
        this.first_brewed = first_brewed;
    }
}
