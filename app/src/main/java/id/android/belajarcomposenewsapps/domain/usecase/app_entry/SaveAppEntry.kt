package id.android.belajarcomposenewsapps.domain.usecase.app_entry

import id.android.belajarcomposenewsapps.domain.pref.LocalUserPref

class SaveAppEntry(
    private val localUserManger: LocalUserPref
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}