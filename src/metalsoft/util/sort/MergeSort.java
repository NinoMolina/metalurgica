/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util.sort;

/**
 *
 * @author Nino
 */
public class MergeSort {

    private int[] array;

    public MergeSort(int[] _array)
    {
	array = _array;
    }

    public int[] getArray()
    {
	return array;
    }


    public void Ordenar()
    {
	array = Ordenar(array);
    }

    //Metodo recursivo
    private int[] Ordenar(int[] _array)
    {
	if (_array.length == 1)
        {
	    return _array;
	}
	else
        {
	    int[] left = new int[_array.length/2];
	    System.arraycopy(_array, 0, left, 0, left.length);


	    int[] right = new int[_array.length-left.length];
	    System.arraycopy(_array, left.length, right, 0, right.length);


	    left = Ordenar(left);//recursividad
	    right = Ordenar(right);//recursividad

	    merge(left, right, _array);

	    return _array;
	}
    }

    private void merge(int[] left, int[] right, int[] _array)
    {
	int leftIndex = 0;
	int rightIndex = 0;
	int arrayIndex = 0;

	while (leftIndex < left.length &&
	       rightIndex < right.length)
        {
	    if (left[leftIndex] < right[rightIndex])
            {
		_array[arrayIndex] = left[leftIndex];
		leftIndex++;
	    }
	    else
            {
		_array[arrayIndex] = right[rightIndex];
		rightIndex++;
	    }
	    arrayIndex++;
	}

	int[] rest;
	int restIndex;
	if (leftIndex >= left.length)
        {
	    rest = right;
	    restIndex = rightIndex;
	}
	else
        {
	    rest = left;
	    restIndex = leftIndex;
	}

	for (int i=restIndex; i<rest.length; i++)
        {
	    _array[arrayIndex] = rest[i];
	    arrayIndex++;
	}
    }
}
