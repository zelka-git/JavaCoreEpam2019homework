package ru.epam.javacore.homework20200205.common.solutions.utils;

import ru.epam.javacore.homework20200205.common.business.domain.BaseEntity;
import ru.epam.javacore.homework20200205.storage.IdGenerator;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ListUtils {
    private ListUtils() {
    }

    public static <T extends BaseEntity> void addToList(T element, List<T> list) {
        element.setId(IdGenerator.generateId());
        list.add(element);
    }

    public static <T extends BaseEntity> Optional<T> getByIdFromList(long id, List<T> list) {
        for (T item : list) {
            if (Optional.ofNullable(item).map(e -> e.getId().equals(id)).orElse(false)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
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
