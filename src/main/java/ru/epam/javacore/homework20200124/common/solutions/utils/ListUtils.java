package ru.epam.javacore.homework20200124.common.solutions.utils;

import ru.epam.javacore.homework20200124.common.business.domain.BaseEntity;
import ru.epam.javacore.homework20200124.storage.IdGenerator;

import java.util.Iterator;
import java.util.List;

public class ListUtils {
    private ListUtils() {
    }

    public static <T extends BaseEntity> void addToList(T element, List<T> list) {
        element.setId(IdGenerator.generateId());
        list.add(element);
    }

    public static <T extends BaseEntity> T getByIdFromList(long id, List<T> list) {
        for (T item : list) {
            if (item.getId() != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public static <T extends BaseEntity> boolean deleteByIdFromList(long id, List<T> list) {
        Iterator<T> iter = list.iterator();
        while (iter.hasNext()) {
            T element = iter.next();
            if (Long.valueOf(id).equals(element.getId())) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
