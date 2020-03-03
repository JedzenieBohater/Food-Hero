
export const getAccountData = async user => {
  console.log(user)
  const response = await fetch('/account/' + user.userID, {
    method: 'GET',
    credentials: 'include'
  })
  const { accountData } = await response.json()
  return accountData

}
