package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        for (int i = size - 1; i >= 0; i--) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        int resumeIndex = getResumeIndex(resume.getUuid());
        if (resumeIndex == -1) {
            if (size == storage.length) {
                System.out.println("Error. No free space for resumes in the storage");
            } else {
                storage[size] = resume;
                size++;
            }
        } else {
            System.out.println("Error. Resume with ID - " + resume.getUuid() + " already create");
        }
    }

    public void update(Resume resume) {
        int resumeIndex = getResumeIndex(resume.getUuid());
        if (resumeIndex == -1) {
            System.out.println("Error. Resume with ID - " + resume.getUuid() + " not found");
        } else {
            storage[resumeIndex] = resume;
        }

    }

    public Resume[] getStorage() {
        return storage;
    }

    public Resume get(String uuid) {
        Resume resume = null;
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex == -1) {
            System.out.println("Error. Resume with ID - " + uuid + " not found");
        } else {
            resume = storage[resumeIndex];
        }
        return resume;
    }

    public void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex == -1) {
            System.out.println("Error. Resume with ID - " + uuid + " not found");
        } else {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return size;
    }

    private int getResumeIndex(String uuid) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                result = i;
                break;
            }
        }
        return result;
    }
}
