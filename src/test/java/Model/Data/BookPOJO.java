package Model.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
public class BookPOJO {
    private String name;
    private int category_id;
    private int price;
    private String release_date;
    private ArrayList<Integer> image_ids;
    private Boolean status;
}
