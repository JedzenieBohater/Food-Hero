import React from 'react'

export default (props) => {
  return (
    <div id={props.id} className='content-box flexcolumn'>
      <div className='flexrow'>
      <div className=''>
        <img className='pic' alt='' src={props.picture} />
        <div class="star-ratings-css-profile">
          <div class="star-ratings-css-top" style={{width: props.ocena/5*100 + '%'}}><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
          <div class="star-ratings-css-bottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
       </div>
      </div>
      <div className='col75'>
        <div className='title'>
          <b>{props.title}</b>
        </div>
        <div className='flexrow'>
          <div className='marginer'>
          {props.wystawiajacy}
          </div>
          <div className='marginer'>
          {props.data}
          </div>
          <div className='marginer'>
          {props.lokalizacja}
          </div>
        </div>
        <span className='description marginer'>
        {props.opis}
        </span>
      </div>
      </div>
    </div>
  )
}