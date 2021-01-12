package com.example.readingrighths;



public class food {
    private String name;
    private String imageUrl;
    private String instructions;
    private String category;
    public food(String nm, String img, String inst, String cat){
        name=nm;
        imageUrl=img;
        instructions=inst;
        category=cat;
    }
    public food(){
        name=null;
        imageUrl=null;
        instructions=null;
        category=null;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
