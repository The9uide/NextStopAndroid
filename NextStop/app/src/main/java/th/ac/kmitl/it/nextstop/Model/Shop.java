package th.ac.kmitl.it.nextstop.Model;

/**
 * Created by v-trrata on 5/26/2017.
 */

public class Shop {
    private String name;
    private String category;
    private String image;

    public Shop(String name, String category, String image) {
        this.name = name;
        this.category = category;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
