package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Error. No free space for resumes in the storage");
        } else if (getResumeIndex(resume.getUuid()) != -1) {
            System.out.println("Error. Resume with ID - " + resume.getUuid() + " already create");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int index = getResumeIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Error. Resume with ID - " + resume.getUuid() + " not found");
        } else {
            storage[index] = resume;
        }

    }

    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);
        if (index == -1) {
            System.out.println("Error. Resume with ID - " + uuid + " not found");
        } else {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getResumeIndex(uuid);
        if (index == -1) {
            System.out.println("Error. Resume with ID - " + uuid + " not found");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;

            }
        }
        return -1;
    }
}
