import React from 'react'

export default props => {
  return (
    <div id={props.id} className="content-box flexcolumn offer">
      <div className="flexrow">
        <div className="">
          <img className="pic" alt="" src={props.picture} />
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
            <div className="marginer">{props.location}</div>
          </div>
          <span className="description marginer">{props.description}</span>
        </div>
      </div>
    </div>
  )
}
