package fr.codingfactory.scanner

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import fr.codingfactory.scanner.data.Item
import fr.codingfactory.scanner.data.ItemDao
import fr.codingfactory.scanner.foodlist.FoodListViewModel
import fr.codingfactory.scanner.foodlist.FoodListViewModelState
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class FoodViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `load user yields state Success`() {
        val model = FoodListViewModel()
        val observer = model.getState().testObserver()

        val newFood = Item(id = 0,
                title = "Coca",
                scanDate = "25-03-2021",
                scanHour = "10h25",
                itemImageUrl = "https://img-19.ccm2.net/8vUCl8TXZfwTt7zAOkBkuDRHiT8=/1240x/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg",
                nutrition_grades = "e",
                ingredients_text_fr = "coco")

        val expectedFood = Item(
                id = 1,
                title = "Coca",
                scanDate = "25-03-2021",
                scanHour = "10h25",
                itemImageUrl = "https://img-19.ccm2.net/8vUCl8TXZfwTt7zAOkBkuDRHiT8=/1240x/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg",
                nutrition_grades = "e",
                ingredients_text_fr = "coco"
        )

        val item = mock<ItemDao>()
        //whenever(item.addItem(newFood)).thenReturn()

        Assert.assertEquals(
                listOf(
                        FoodListViewModelState.Loading,
                       // FoodListViewModelState.LoadedItems(),
                ),
                observer.observedValues
        )
    }
}
