package id.co.quadras.winwork.model.vo;

import id.co.quadras.winwork.util.PojoUtil;

/**
 * @author irwin Timestamp : 12/04/13 17:04
 */
public class KeyValue {

    private final String key;
    private final String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return PojoUtil.beanToString(this, false);
    }

}
