/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = size() - 1; i >= 0; i--) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume[] getStorage() {
        return storage;
    }

    Resume get(String uuid) {
        for (int i = size() - 1; i >= 0; i--) {
            if (uuid.equals(storage[i].toString())) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(storage[i].toString())) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                break;
            }
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];

        for (int i = 0; i < size(); i++) {
            resumes[i] = storage[i];
        }

        return resumes;
    }

    int size() {
        return size;
    }
}
