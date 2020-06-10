import React, { useState, useEffect } from 'react'
import { withRouter } from 'react-router'
import { getImage } from 'utils/dish'

var List = props => {

  const [dishImg, setDishImg] = useState("")

  var redirectToOfferDetails = () => {
    //console.log(props.id)
    props.history.push("/offer/" + props.id);
  }

  useEffect(() => {
    setDishImg(getImage(props.id))
    console.log(props)
  }, [])

  //console.log(props)
  return (
    <div id={props.id} className="content-box flexcolumn offer" onClick={() => redirectToOfferDetails()}>
      <div className="flexrow">
        <div className="">
          <img className="pic" alt="" src={dishImg} />
          <div className="star-ratings-css">
            <div
              className="star-ratings-css-top"
              style={{ width: (props.grade / 5) * 100 + '%' }}
            >
              <span>★</span>
              <span>★</span>
              <span>★</span>
              <span>★</span>
              <span>★</span>
            </div>
            <div className="star-ratings-css-bottom">
              <span>★</span>
              <span>★</span>
              <span>★</span>
              <span>★</span>
              <span>★</span>
            </div>
          </div>
        </div>
        <div className="col75">
          <div className="title">
            <b>{props.title}</b>
          </div>
          <div className="flexrow">
            <div className="marginer price">{props.price} zł</div>
            <div className="marginer">{props.cook}</div>
            <div className="marginer">{props.date}</div>
            <div className="marginer">{props.localization}</div>
          </div>
          <span className="description marginer">{props.description}</span>
        </div>
      </div>
    </div>
  )
}



export default withRouter(List)
//export default List