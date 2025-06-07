package model;

public class Item {
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;

    // Конструктор по умолчанию
    public Item() {}

    // Геттеры
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public String getImage() {
        return image;
    }

    // Сеттеры (на будущее, если будешь добавлять/изменять товары)
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setImage(String image) {
        this.image = image;
    }
}