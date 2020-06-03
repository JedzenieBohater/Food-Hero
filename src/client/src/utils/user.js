export const getAccountData = async user => {
  const response = await fetch('/api/account/' + user.userID)
  let accountData = await response.json()
  return accountData
}

export const sendChangedProfile = async (object, user) => {
  fetch('/api/account/update/' + user.userID, {
    method: 'PUT',
    body: JSON.stringify(object),
    headers: {
      'Content-Type': 'application/json',
    },
  })
}
