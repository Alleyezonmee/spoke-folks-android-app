package com.soulinc.spokeFolks.account.viewmodel

import com.soulinc.spokeFolks.account.data.repo.IAccountRepository
import com.soulinc.spokeFolks.base.BaseViewModel
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val repo: IAccountRepository) : BaseViewModel() {


}