package id.my.hendisantika.springbootdatacassandra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-data-cassandra
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/25/24
 * Time: 12:25
 * To change this template use File | Settings | File Templates.
 */
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    @PrimaryKey
    private UUID id;

    private String title;
    private String description;
    private boolean published;
}
