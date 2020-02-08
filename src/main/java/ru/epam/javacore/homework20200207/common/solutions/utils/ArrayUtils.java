package ru.epam.javacore.homework20200207.common.solutions.utils;

import ru.epam.javacore.homework20200207.common.business.domain.BaseEntity;
import ru.epam.javacore.homework20200207.storage.IdGenerator;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    public static void copyArray(Object[] src, Object[] dest) {
        System.arraycopy(src, 0, dest, 0, src.length);
    }

    public static void copyArray(Object[] src, Object[] dest, int len) {
        System.arraycopy(src, 0, dest, 0, len);
    }

    public static void printArray(Object[] arr) {
        for (Object obj : arr) {
            Optional.ofNullable(obj).ifPresent(System.out::println);
        }
    }

    public static void printArray(List<?> list) {
        list.forEach(System.out::println);
    }

    public static <T extends BaseEntity> int addToArray(T element, T[] array, int sizeArray, Class<? extends T> clazz) {
        if (sizeArray == array.length) {
            T[] newArray;
            newArray = (T[]) Array.newInstance(clazz, (int) (sizeArray * 1.5));
            ArrayUtils.copyArray(array, newArray);
            array = newArray;
        }
        element.setId(IdGenerator.generateId());
        array[sizeArray] = element;
        return ++sizeArray;
    }

    public static <T extends BaseEntity> Optional<T> getByIdFromArray(long id, T[] array) {
        for (T item : array) {
            if (item != null && Long.valueOf(id).equals(item.getId())) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public static <T extends BaseEntity> boolean deleteByIdFromArray(long id, T[] array, int sizeArray) {
        if (array != null) {
            for (int i = 0; i < sizeArray; i++) {
                if (array[i] != null && Long.valueOf(id).equals(array[i].getId())) {
                    System.arraycopy(array, i + 1, array, i, array.length - (i + 1));
                    array[array.length - 1] = null;
                    return true;
                }
            }
        }
        return false;
    }

}