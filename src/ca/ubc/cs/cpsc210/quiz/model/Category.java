package ca.ubc.cs.cpsc210.quiz.model;

/**
 * Created by Ting Ting Tai on 2014-10-25.
 * Represents a category of restaurant with name and alias.
 */
public class Category {

    private String name;
    private String alias;

    public Category(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!alias.equals(category.alias)) return false;
        if (!name.equals(category.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + alias.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

}


















