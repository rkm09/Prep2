package GenDS;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class Pair<K,V> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int key;
    private int value;
    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
