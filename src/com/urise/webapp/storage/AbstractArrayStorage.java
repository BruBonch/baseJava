package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.printf("Resume %s not exist \n", uuid);
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Error. No free space for resumes in the storage");
        } else if (index > 0) {
            System.out.println("Error. Resume with ID - " + resume.getUuid() + " already create");
        } else {
            insertResumeInStorage(resume, -index);
            size++;
        }
    }

    protected abstract void insertResumeInStorage(Resume resume, int index);

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Error. Resume with ID - " + resume.getUuid() + " not found");
        } else {
            storage[index] = resume;
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Error. Resume with ID - " + uuid + " not found");
        } else {
            fillPlaceInStorage(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void fillPlaceInStorage(int index);

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }
}
