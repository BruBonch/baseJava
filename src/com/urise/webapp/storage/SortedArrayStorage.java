package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void fillPlaceInStorage(int index) {
        if (index != size - 1) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
    }

    @Override
    protected void insertResumeInStorage(Resume resume, int index) {
        System.arraycopy(storage, index - 1, storage, index, size);
        storage[index - 1] = resume;
    }
}
