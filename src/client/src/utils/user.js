
export const getAccountData = async user => {
  const response = await fetch('/account/' + user.userID, {
    method: 'GET',
    credentials: 'include'
  })
  let accountData = await response.json()
  return accountData

}

export const sendChangedProfile = async (object, user) => {
  console.log(user.userID)
  fetch('/account/update/' + user.userID, {
    method: 'PUT',
    body: JSON.stringify(object),
    headers: {
      'Content-Type': 'application/json'
    },
    credentials: 'include'
  })
}
