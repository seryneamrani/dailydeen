package com.dailydeen

import android.app.Application
import androidx.room.Room
import com.dailydeen.data.local.DailyDeenDatabase
import com.dailydeen.data.local.DataStoreManager
import com.dailydeen.data.local.entity.*
import com.dailydeen.data.repository.DailyDeenRepository
import com.dailydeen.worker.DailyReminderWorker
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class DailyDeenApp : Application() {
    lateinit var database: DailyDeenDatabase
    lateinit var repository: DailyDeenRepository
    lateinit var dataStoreManager: DataStoreManager
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            DailyDeenDatabase::class.java,
            "dailydeen_db"
        ).build()
        repository = DailyDeenRepository(database.dailyDeenDao())
        dataStoreManager = DataStoreManager(this)

        seedDatabase()
        scheduleDailyReminders()
    }

    private fun scheduleDailyReminders() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val reminderRequest = PeriodicWorkRequestBuilder<DailyReminderWorker>(24, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "daily_reminder",
            ExistingPeriodicWorkPolicy.KEEP,
            reminderRequest
        )
    }


    private fun seedDatabase() {
                applicationScope.launch {
                    val dao = database.dailyDeenDao()

                    // -------- CHALLENGES --------
                    if (dao.countChallenges() == 0) {
                        listOf(
                            "Morning Adhkars" to "Recite morning adhkars after Fajr",
                            "Evening Adhkars" to "Recite evening adhkars before sleeping",
                            "Read Quran (2 pages)" to "Read at least 2 pages of Qur'an today",
                            "Listen to Quran (10 min)" to "Listen to Qur'an recitation for 10 minutes",
                            "Dhuha Prayer" to "Perform at least 2 rak'ahs of Dhuha",
                            "Pray Witr" to "Pray Witr before sleeping",
                            "Pray on time" to "Try to pray at least one prayer on time today",
                            "Sunnah before Fajr" to "Pray 2 rak'ahs Sunnah before Fajr",
                            "Sunnah after Dhuhr" to "Pray Sunnah after Dhuhr",
                            "Send Salawat" to "Send blessings upon the Prophet ﷺ 50 times",
                            "Say Astaghfirullah" to "Seek forgiveness (Astaghfirullah) 100 times",
                            "Give Charity" to "Give any amount of charity (even small)",
                            "Help someone" to "Help a family member, friend, or neighbor",
                            "Smile" to "Smile to someone (it's Sunnah!)",
                            "Good words" to "Say something kind to someone today",
                            "Avoid backbiting" to "Avoid gossip/backbiting for the day",
                            "Lower the gaze" to "Be mindful of lowering the gaze today",
                            "Dhikr after Salah" to "Do tasbih after at least one prayer",
                            "Learn 1 Ayah" to "Memorize or reflect on one verse today",
                            "Make Dua" to "Make sincere dua for yourself and others"
                        ).forEach { (title, desc) ->
                            dao.insertChallenge(Challenge(title = title, description = desc))
                        }
                    }

                    // -------- ADHKARS --------
                    if (dao.countAdhkars() == 0) {

                        // GENERAL
                        listOf(
                            "SubhanAllah" to "Glory be to Allah",
                            "Alhamdulillah" to "Praise be to Allah",
                            "Allahu Akbar" to "Allah is the Greatest",
                            "La ilaha illa Allah" to "There is no god but Allah",
                            "Astaghfirullah" to "I seek Allah's forgiveness",
                            "SubhanAllahi wa bihamdihi" to "Glory and praise be to Allah",
                            "SubhanAllahil Azim" to "Glory be to Allah the Great",
                            "Hasbiyallahu la ilaha illa Huwa" to "Allah is sufficient for me",
                            "La hawla wa la quwwata illa billah" to "There is no power nor strength except with Allah",
                            "Allahumma salli 'ala Muhammad" to "O Allah, send blessings upon Muhammad",
                            "Allahumma salli wa sallim 'ala Nabiyyina Muhammad" to "O Allah, send blessings and peace upon our Prophet Muhammad",
                            "SubhanAllah wa Alhamdulillah wa La ilaha illa Allah wa Allahu Akbar" to "Glorified, praised, none worthy but Allah, Allah is greatest",
                            "Alhamdulillah 'ala kulli hal" to "All praise is due to Allah in every situation",
                            "Rabbighfir li" to "My Lord, forgive me",
                            "Ya Hayyu Ya Qayyum" to "O Ever-Living, O Sustainer",
                            "Allahumma inni as'aluka al-'afiyah" to "O Allah, I ask You for well-being",
                            "Allahumma inni a'udhu bika min al-hammi wal-hazan" to "O Allah, I seek refuge from worry and sadness",
                            "Inna lillahi wa inna ilayhi raji'un" to "To Allah we belong and to Him we return",
                            "Bismillah" to "In the name of Allah",
                            "Allahumma barik lana" to "O Allah, bless us"
                        ).forEach { (content, translation) ->
                            dao.insertAdhkar(
                                Adhkar(content = content, translation = translation, targetCount = 33, category = "general")
                            )
                        }

                        // MORNING
                        listOf(
                            "Ayat al-Kursi" to "The Throne Verse",
                            "Allahumma bika asbahna" to "O Allah, by You we enter the morning",
                            "Asbahna wa asbahal-mulk lillah" to "We have entered the morning and the dominion belongs to Allah",
                            "Raditu billahi rabban" to "I am pleased with Allah as Lord",
                            "Bismillah illadhi la yadurru" to "In the name of Allah with whose name nothing harms",
                            "Allahumma inni as'aluka khayra hadha al-yawm" to "O Allah, I ask You for the goodness of this day",
                            "Allahumma anta rabbi la ilaha illa ant" to "O Allah, You are my Lord, none worthy of worship but You",
                            "SubhanAllahi wa bihamdihi" to "Glory and praise be to Allah",
                            "Astaghfirullah" to "I seek Allah's forgiveness",
                            "Allahu Akbar" to "Allah is the Greatest",
                            "La ilaha illa Allah wahdahu la sharika lah" to "None has the right to be worshipped but Allah alone",
                            "Hasbiyallahu la ilaha illa Huwa" to "Allah is sufficient for me",
                            "Allahumma inni as'aluka al-'afiyah" to "O Allah, I ask You for well-being",
                            "Allahumma 'afini fi badani" to "O Allah, grant my body health",
                            "Allahumma inni a'udhu bika min al-kufr wal-faqr" to "O Allah, I seek refuge from disbelief and poverty",
                            "Allahumma inni a'udhu bika min 'adhab al-qabr" to "O Allah, I seek refuge from the punishment of the grave",
                            "Allahumma inni a'udhu bika min hamazat ash-shayatin" to "O Allah, I seek refuge from the whispers of devils",
                            "Allahumma inni as'aluka rizqan tayyiban" to "O Allah, I ask You for good provision",
                            "Allahumma ajirni min an-nar" to "O Allah, protect me from the Fire",
                            "Allahumma inni as'aluka hubbaka" to "O Allah, I ask You for Your love"
                        ).forEach { (content, translation) ->
                            dao.insertAdhkar(
                                Adhkar(content = content, translation = translation, targetCount = 1, category = "morning")
                            )
                        }

                        // EVENING
                        listOf(
                            "Ayat al-Kursi" to "The Throne Verse",
                            "Allahumma bika amsayna" to "O Allah, by You we enter the evening",
                            "Amsayna wa amsal-mulk lillah" to "We have entered the evening and the dominion belongs to Allah",
                            "Bismillah illadhi la yadurru" to "In the name of Allah with whose name nothing harms",
                            "A'udhu bi kalimatillahi at-tammati" to "I seek refuge in Allah's perfect words",
                            "Allahumma inni as'aluka khayra hadha al-laylah" to "O Allah, I ask You for the goodness of this night",
                            "Allahumma anta rabbi la ilaha illa ant" to "O Allah, You are my Lord, none worthy of worship but You",
                            "SubhanAllahi wa bihamdihi" to "Glory and praise be to Allah",
                            "Astaghfirullah" to "I seek Allah's forgiveness",
                            "La ilaha illa Allah wahdahu la sharika lah" to "None has the right to be worshipped but Allah alone",
                            "Hasbiyallahu la ilaha illa Huwa" to "Allah is sufficient for me",
                            "Allahumma inni as'aluka al-'afiyah" to "O Allah, I ask You for well-being",
                            "Allahumma 'afini fi badani" to "O Allah, grant my body health",
                            "Allahumma inni a'udhu bika min al-kufr wal-faqr" to "O Allah, I seek refuge from disbelief and poverty",
                            "Allahumma inni a'udhu bika min 'adhab al-qabr" to "O Allah, I seek refuge from the punishment of the grave",
                            "Allahumma inni a'udhu bika min hamazat ash-shayatin" to "O Allah, I seek refuge from the whispers of devils",
                            "Allahumma inni as'aluka rizqan tayyiban" to "O Allah, I ask You for good provision",
                            "Allahumma ajirni min an-nar" to "O Allah, protect me from the Fire",
                            "Allahumma inni as'aluka hubbaka" to "O Allah, I ask You for Your love",
                            "Allahumma inni a'udhu bika min sharri ma sana't" to "O Allah, I seek refuge from the evil of what I have done"
                        ).forEach { (content, translation) ->
                            dao.insertAdhkar(
                                Adhkar(content = content, translation = translation, targetCount = 1, category = "evening")
                            )
                        }
                    }

                    // -------- HADITHS --------
                    if (dao.countHadiths() == 0) {
                        listOf(
                            "Actions are judged by intentions." to "Bukhari & Muslim",
                            "The best among you are those who have the best manners." to "Bukhari",
                            "Smiling in your brother’s face is charity." to "Tirmidhi",
                            "Allah is gentle and loves gentleness." to "Muslim",
                            "The strong man is the one who controls his anger." to "Bukhari",
                            "The best of you are those who learn the Qur’an and teach it." to "Bukhari",
                            "Make things easy and do not make them difficult." to "Bukhari",
                            "Cleanliness is half of faith." to "Muslim",
                            "None of you truly believes until he loves for his brother what he loves for himself." to "Bukhari & Muslim",
                            "The world is provision, and the best provision is a righteous wife." to "Muslim",
                            "Whoever remains silent is saved." to "Tirmidhi",
                            "Allah does not look at your appearance, but at your hearts." to "Muslim",
                            "Seek knowledge from the cradle to the grave." to "Bayhaqi",
                            "The most beloved deeds to Allah are the consistent ones." to "Bukhari",
                            "Prayer is the pillar of religion." to "Tabarani",
                            "Patience is light." to "Muslim",
                            "The best charity is given in Ramadan." to "Tirmidhi",
                            "Allah loves that when one does a job, he perfects it." to "Bayhaqi",
                            "Whoever eases hardship, Allah will ease his." to "Muslim",
                            "The best speech is the Book of Allah." to "Muslim"
                        ).forEach { (content, source) ->
                            dao.insertHadith(Hadith(content = content, source = source))
                        }
                    }

                    // -------- QUIZ --------
                    if (dao.countQuizQuestions() == 0) {

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "How many pillars of Islam are there?",
                            options = listOf("3", "5", "7", "10"),
                            correctAnswerIndex = 1,
                            explanation = "There are 5 pillars: Shahada, Salah, Zakat, Sawm, and Hajj."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "Which month is the month of fasting?",
                            options = listOf("Muharram", "Ramadan", "Shawwal", "Dhul-Hijjah"),
                            correctAnswerIndex = 1,
                            explanation = "Ramadan is the 9th month of the Islamic calendar."
                        ))
                        dao.insertQuizQuestion(QuizQuestion(
                            question = "How many daily obligatory prayers are there?",
                            options = listOf("3", "5", "6", "7"),
                            correctAnswerIndex = 1,
                            explanation = "There are 5 daily obligatory prayers."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "What is the first pillar of Islam?",
                            options = listOf("Salah", "Zakat", "Shahada", "Hajj"),
                            correctAnswerIndex = 2,
                            explanation = "Shahada is the first pillar of Islam."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "Which direction do Muslims face during prayer?",
                            options = listOf("Madina", "Jerusalem", "Kaaba in Makkah", "Arafat"),
                            correctAnswerIndex = 2,
                            explanation = "Muslims face the Kaaba in Makkah."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "How many rak'ahs are in the Fajr prayer?",
                            options = listOf("2", "3", "4", "5"),
                            correctAnswerIndex = 0,
                            explanation = "Fajr consists of 2 obligatory rak'ahs."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "Which charity is obligatory in Islam?",
                            options = listOf("Sadaqah", "Zakat", "Waqf", "Gift"),
                            correctAnswerIndex = 1,
                            explanation = "Zakat is an obligatory charity."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "What is the Islamic greeting?",
                            options = listOf("Hello", "Good morning", "As-salamu alaykum", "Peace"),
                            correctAnswerIndex = 2,
                            explanation = "The Islamic greeting is 'As-salamu alaykum'."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "What is the name of the night better than a thousand months?",
                            options = listOf("Laylat al-Miraj", "Laylat al-Qadr", "Laylat al-Eid", "Laylat al-Jumu'ah"),
                            correctAnswerIndex = 1,
                            explanation = "Laylat al-Qadr is better than a thousand months."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "Which calendar do Muslims follow?",
                            options = listOf("Solar calendar", "Gregorian calendar", "Lunar (Hijri) calendar", "Julian calendar"),
                            correctAnswerIndex = 2,
                            explanation = "Muslims follow the lunar Hijri calendar."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "Who is the last Prophet in Islam?",
                            options = listOf("Isa (Jesus)", "Musa (Moses)", "Muhammad ﷺ", "Ibrahim (Abraham)"),
                            correctAnswerIndex = 2,
                            explanation = "Prophet Muhammad ﷺ is the final messenger."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "How many pillars of Iman (faith) are there?",
                            options = listOf("5", "6", "7", "8"),
                            correctAnswerIndex = 1,
                            explanation = "There are 6 pillars of Iman."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "In which language was the Qur'an revealed?",
                            options = listOf("Arabic", "Hebrew", "Aramaic", "Latin"),
                            correctAnswerIndex = 0,
                            explanation = "The Qur'an was revealed in Arabic."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "Which prayer is performed on Friday at noon?",
                            options = listOf("Dhuhr", "Asr", "Jumu'ah", "Isha"),
                            correctAnswerIndex = 2,
                            explanation = "Jumu'ah replaces Dhuhr on Fridays."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "What breaks the fast?",
                            options = listOf("Sleeping", "Eating", "Making dua", "Walking"),
                            correctAnswerIndex = 1,
                            explanation = "Eating breaks the fast."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "How many months are there in the Islamic calendar?",
                            options = listOf("10", "11", "12", "13"),
                            correctAnswerIndex = 2,
                            explanation = "There are 12 months in the Islamic calendar."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "Which city is the Prophet’s mosque located in?",
                            options = listOf("Makkah", "Taif", "Madina", "Jerusalem"),
                            correctAnswerIndex = 2,
                            explanation = "The Prophet’s mosque is in Madina."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "What does the word 'Islam' mean?",
                            options = listOf("Peace", "Submission", "Prayer", "Faith"),
                            correctAnswerIndex = 1,
                            explanation = "Islam means submission to Allah."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "How many Surahs are in the Qur'an?",
                            options = listOf("99", "110", "114", "120"),
                            correctAnswerIndex = 2,
                            explanation = "The Qur'an contains 114 Surahs."
                        ))

                        dao.insertQuizQuestion(QuizQuestion(
                            question = "At what time does the fast end?",
                            options = listOf("Sunrise", "Noon", "Sunset", "Midnight"),
                            correctAnswerIndex = 2,
                            explanation = "The fast ends at sunset (Maghrib)."
                        ))

                    } // ferme if (dao.countQuizQuestions() == 0)
                }     // ferme applicationScope.launch
    }                 // ferme seedDatabase()
}                     // ferme class DailyDeenApp

