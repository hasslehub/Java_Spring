package org.example;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Person {

    private String firstName;
    private String lastName;
    private int age;
    transient private int[] aa = {21, 3, 4, 5, 6, 6};

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(firstName)
                .append(lastName)
                .append(age)
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Person rhs = (Person) obj;
        return new EqualsBuilder()
                //.appendSuper(super.equals(obj))
                .append(firstName, rhs.firstName)
                .append(lastName, rhs.lastName)
                .append(age, rhs.age)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("firstName", firstName).
                append("lastName", lastName).
                append("age", age).
                append("aa", aa).
                toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
