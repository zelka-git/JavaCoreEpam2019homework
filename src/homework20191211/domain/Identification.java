package homework20191211.domain;

public class Identification {
    protected Long id = ++count;
    private static long count;
    public Long getId() {
        return id;
    }

}
