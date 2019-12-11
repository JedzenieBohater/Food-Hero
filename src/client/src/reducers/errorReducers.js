export function errorReducer(state, action) {
  switch (action.type) {
    case 'CLEAR_ERRORS':
      return state
    case 'RECEIVE_ERRORS':
      return state
    default:
      return state
  }
}
