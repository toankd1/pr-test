package toankd.cn.prtest.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private Integer old;

    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }
}
