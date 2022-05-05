
public class NumericalArrayList {

    int Length = 0;
    int[] ElementContainer = new int[0];

    void Add(int NewElement){
        Length = Length + 1;
        int[] BackupContainer = new int[Length - 1];
        for(int i = 0; i < Length - 1; i++)
            BackupContainer[i] = ElementContainer[i];

        ElementContainer = new int[Length];
        for(int i = 0; i < Length - 1; i++)
            ElementContainer[i] = BackupContainer[i];

        ElementContainer[Length - 1] = NewElement;
    }

    int Get(int Index){
        return ElementContainer[Index];
    }

    void Set(int Index, int Value){
        ElementContainer[Index] = Value;
    }

    void Remove(int Index){
        Length = Length - 1;
        int[] BackupContainer = new int[Length];
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

        ElementContainer = new int[Length];
        for(int i = 0; i < Length; i++)
            ElementContainer[i] = BackupContainer[i];
    }

    void Clear(){
        ElementContainer = new int[0];
        Length = 0;
    }

    int Size(){
        return Length;
    }

}
