import React, { useState, useEffect } from 'react'
import { withRouter } from 'react-router'

var List = props => {
  const [dishImgUrl, setDishImgUrl] = useState(null)

  var redirectToOfferDetails = () => {
    //console.log(props.id)
    props.history.push('/offer/' + props.id)
  }

  useEffect(() => {
    ;(async () => {
      try {
        const response = await fetch(`/api/dish/${props.id}/image`)

        if (!response.ok) {
          throw new Error('Getting dish img not ok')
        }

        const data = await response.blob()
        const url = URL.createObjectURL(data)
        setDishImgUrl(url)
      } catch (err) {
        console.log(err)
      }
    })()
  }, [])

  return (
    <div
      id={props.id}
      className="content-box flexcolumn offer"
      onClick={() => redirectToOfferDetails()}
    >
      <div className="flexrow">
        <div className="">
          <img
            className="pic"
            alt=""
            src={
              !!dishImgUrl
                ? dishImgUrl
                : `${process.env.PUBLIC_URL}/static/images/logo.svg`
            }
          />
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
            <div className="marginer">{props.name}</div>
            <div className="marginer">{props.category}</div>
          </div>
          <span className="description marginer">{props.description}</span>
        </div>
      </div>
    </div>
  )
}

export default withRouter(List)
