export const getUserDishes = async id => {
  try {
    const response = await fetch(`/api/account/${id}/dishes`)

    if (!response.ok) {
      throw new Error('getUserDishes not ok')
    }

    const data = await response.json()

    return data
  } catch (err) {
    console.log(err)
  }
}

export const deleteDish = id => fetch(`/api/dish/${id}`, { method: 'DELETE' })
