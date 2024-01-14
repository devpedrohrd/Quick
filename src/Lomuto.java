public class Lomuto implements App.SortingFunction {
    private int trocas; // variável para contar o número de trocas

    @Override
    public int[] sort(int[] arr) {
        trocas = 0;
        quickSortLomuto(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSortLomuto(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionLomuto(arr, low, high);
            quickSortLomuto(arr, low, pivotIndex - 1);
            quickSortLomuto(arr, pivotIndex + 1, high);
        }
    }

    private int partitionLomuto(int[] arr, int low, int high) {
        int pivot = arr[high]; 
        int i = low - 1; // Índice do menor elemento

        for (int j = low; j <= high - 1; j++) { // Percorre o array
            // Se o elemento atual for menor ou igual ao pivô
            if (arr[j] <= pivot) {
                i++;
                // Troca arr[i] e arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                trocas++;
            }
        }

        // Troca arr[i+1] e arr[high] (pivô)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        trocas++;

        return i + 1;
    }

    public int getTrocas() {
        return trocas;
    }
}