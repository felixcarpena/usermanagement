package shared.infrastructure.persistence;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "event_store", uniqueConstraints = { @UniqueConstraint(name = "aggregate_id_version", columnNames = { "aggregate_id", "version" }) })
@TypeDef(name = "json", typeClass = JsonType.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JpaEventStoreEntity {
    @NonNull @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull @Column(name = "aggregate_id", length = 36, nullable = false)
    private String aggregateId;
    @NonNull @Column(length = 191, nullable = false)
    private String type;
    @NonNull @Column(nullable = false)
    private Long version;
    @NonNull @Type(type = "json") @Column(columnDefinition = "json", nullable = false)
    private String data;
}
