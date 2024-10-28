/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = size() - 1; i >= 0; i--) {
            storage[i] = null;
        }

    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    public Resume[] getStorage() {
        return storage;
    }

    Resume get(String uuid) {
        Resume result = new Resume();
        result.uuid = "Resume not found";

        for (int i = size() - 1; i >= 0; i--) {
            if (uuid.equals(storage[i].toString())) {
                result = storage[i];
                break;
            }
        }
        return result;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {

            if (uuid.equals(storage[i].toString())) {

                for (int j = i; j < size() - 1; j++) {
                    storage[j] = storage[j + 1];
                }

                storage[size() - 1] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resultArray = new Resume[size()];

        for (int i = 0; i < size(); i++) {
            resultArray[i] = storage[i];
        }

        return resultArray;
    }

    int size() {
        int result = 0;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            } else {
                result += 1;
            }
        }
        return result;
    }
}
