public class Hoare implements App.SortingFunction {
    @Override
    public int[] sort(int[] arr) {
        quickSortHoare(arr, 0, arr.length - 1);
        return arr;
    }

    // low = indice do primeiro elemento, high = indice do último elemento
    private void quickSortHoare(int[] arr, int low, int high) { 
        if (low < high) {
            int pivotIndex = partitionHoare(arr, low, high);
            quickSortHoare(arr, low, pivotIndex);
            quickSortHoare(arr, pivotIndex + 1, high);
        }
    }

    /*A função abaixo usa o primeiro elemento como pivô e coloca todos os elementos menores que o pivô 
    no lado esquerdo e todos os elementos maiores que o pivô no lado direito.  
    Ela retorna o índice do último elemento elemento no lado menor */
    
    private int partitionHoare(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            // Encontra o primeiro elemento maior que o pivô
            do {
                i++;
            } while (arr[i] < pivot);

            // Encontra o primeiro elemento menor que o pivô
            do {
                j--;
            } while (arr[j] > pivot);

            // Se os índices se cruzarem, retorna j
            if (i >= j) {
                return j;
            }

            // Troca arr[i] e arr[j]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}