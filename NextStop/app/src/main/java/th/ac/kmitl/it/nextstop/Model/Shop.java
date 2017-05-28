package th.ac.kmitl.it.nextstop.Model;

import android.graphics.Bitmap;

/**
 * Created by v-trrata on 5/26/2017.
 */

public class Shop {
    private String name;
    private String category;
    private Bitmap image;

    public Shop(String name, String category, Bitmap image) {
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
