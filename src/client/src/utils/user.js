
export const getAccountData = async user => {
  const response = await fetch('/account/' + user.userID, {
    method: 'GET',
    credentials: 'include'
  })
  let accountData = await response.json()
  return accountData

}
