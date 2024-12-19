package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public interface Storage {
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    Resume get(String uuid);

    void save(Resume resume);

    void update(Resume resume);

    void delete(String uuid);

    void clear();

    int size();
}
