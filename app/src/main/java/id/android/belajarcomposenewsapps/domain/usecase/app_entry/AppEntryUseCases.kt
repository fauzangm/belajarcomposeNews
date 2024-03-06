package id.android.belajarcomposenewsapps.domain.usecase.app_entry

import id.android.belajarcomposenewsapps.domain.usecase.app_entry.ReadAppEntry
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.SaveAppEntry

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry,
)