    package com.example.fitx
    import com.example.fitx.model.SportTips
    import com.example.fitx.repository.MockAuth
    import com.example.fitx.repository.MockFirestore
    import com.example.fitx.repository.RepositoryTest
    import org.junit.Assert.assertFalse
    import org.junit.Assert.assertNotNull
    import org.junit.Assert.assertTrue
    import org.junit.Test

    class TipsTest {

        // Simulates successful fetching of tips
        @Test
        fun testSuccessfulFetchOfHealthyTips() {

            val mockAuth = MockAuth(true)// Simulates successful authentication
            val mockFirestore = MockFirestore(true)// Simulates successful data saving
            val tipsRepository = RepositoryTest(mockAuth, mockFirestore)


            var fetchedTips: List<SportTips>? = null
            tipsRepository.getHealthyTips { tips ->
                fetchedTips = tips
            }


            assertNotNull(fetchedTips)
            assertFalse(fetchedTips.isNullOrEmpty())
        }

        // Simulates failure in fetching tips
        @Test
        fun testFailureToFetchHealthyTips() {

            val mockAuth = MockAuth(true)// Simulates successful authentication
            val mockFirestore = MockFirestore(false)// Simulates unsuccessful data saving
            val tipsRepository = RepositoryTest(mockAuth, mockFirestore)


            var fetchedTips: List<SportTips>? = null
            tipsRepository.getHealthyTips { tips ->
                fetchedTips = tips
            }


            assertTrue(fetchedTips.isNullOrEmpty())
        }

        //Simulates fetching empty list of tips
        @Test
        fun testFetchingEmptyListOfHealthyTips() {

            val mockAuth = MockAuth(true)// Simulates successful authentication
            val mockFirestore = MockFirestore(true, returnEmptyList = true)// Simulates successful data and empty list
            val tipsRepository = RepositoryTest(mockAuth, mockFirestore)


            var fetchedTips: List<SportTips>? = null
            tipsRepository.getHealthyTips { tips ->
                fetchedTips = tips
            }


            assertTrue(fetchedTips?.isEmpty() ?: false)
        }


    }