package util;


import model.Human;

import java.util.Comparator;


public class FullNameHumanComparator<T extends Human> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        String name1 = String.join("",
                o1.getSecondName(),
                o1.getFirstName(),
                o1.getPatronymicName() == null? "" : o1.getPatronymicName());
        String name2 = String.join("",
                o2.getSecondName(),
                o2.getFirstName(),
                o2.getPatronymicName() == null? "" : o2.getPatronymicName());
        
        return name1.compareTo(name2);
    }
}
