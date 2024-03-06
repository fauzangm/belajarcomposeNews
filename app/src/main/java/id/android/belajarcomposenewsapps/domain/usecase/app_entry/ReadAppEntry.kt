package id.android.belajarcomposenewsapps.domain.usecase.app_entry

import id.android.belajarcomposenewsapps.domain.pref.LocalUserPref
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserPref
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }

}