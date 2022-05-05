import java.lang.reflect.Array;

public class GenericNAL<T> {

    int Length = 0;
    T[] ElementContainer = (T[])new Object[Length];

    void Add(T NewElement){
        Length = Length + 1;
        T[] BackupContainer = (T[])new Object[Length];
        for(int i = 0; i < Length - 1; i++)
            BackupContainer[i] = ElementContainer[i];

        ElementContainer = (T[])new Object[Length];
        for(int i = 0; i < Length - 1; i++)
            ElementContainer[i] = BackupContainer[i];

        ElementContainer[Length - 1] = NewElement;
    }

    T Get(int Index){
        return ElementContainer[Index];
    }

    void Set(int Index, T Value){
        ElementContainer[Index] = Value;
    }

    void Remove(int Index){
        Length = Length - 1;
        T[] BackupContainer = (T[])new Object[Length];
        for(int i = 0; i < Length; i++) {
            if (i < Index) {
                BackupContainer[i] = ElementContainer[i];
                // System.out.printf("test : %d, %d, %d, %d \n", i, Index, BackupContainer[i], ElementContainer[i]);
            }

            if (i >= Index) {
                BackupContainer[i] = ElementContainer[i + 1];
                // System.out.printf("test : %d, %d, %d, %d \n", i, Index, BackupContainer[i], ElementContainer[i + 1]);
            }
        }

        ElementContainer = (T[])new Object[Length];
        for(int i = 0; i < Length; i++)
            ElementContainer[i] = BackupContainer[i];
    }

    void Clear(){
        ElementContainer = (T[])new Object[Length];
        Length = 0;
    }

    int Size(){
        return Length;
    }

}
