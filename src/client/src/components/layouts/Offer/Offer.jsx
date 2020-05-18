import React from 'react'

export default props => {
  
  return (
    <div id={props.id} className="content-box" onClick={() => console.log(props.id)}>
      offer screen, go to payment
    </div>
  )
}
