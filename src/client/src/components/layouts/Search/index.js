import React, { useState, useEffect } from 'react'
import List from './List'
import { connect } from 'react-redux'
import a from './exampledata.json'

const data = a.offers;

export const Search = (props) => {
  // const [data, setData] = useState(undefined)

  // function getData(url){
  //   fetch(url)
  //   .then((response) => {
  //     return response.json();
  //   })
  //   .then((myJson) => {
  //     setData(myJson.offers);
  //   });
  // }

  // useEffect(() => {
  //getData('http://192.168.99.100:5001/js');
  // }, []);
  
  return (
    <div>
      <div className='content-box searchmain flexing search'>
        <label htmlFor="what">{props.lang.what} </label>
        <input id='what' type='text' />
        <label htmlFor="localization">{props.lang.localization} </label>
        <input id='localization' type='text' />
        <button className="btn-blue">{props.lang.search} </button>
      </div>
      <div className="searchmain flexing">
        <div className='content-box col25'>{props.lang.filters}</div>
        <div className='content-box col75 centering'>
          {data ?
            data.map(offer => (<List
              key={offer.id}
              id={offer.id}
              picture={offer.picture}
              title={offer.title}
              cook={offer.cook}
              date={offer.date}
              location={offer.location}
              grade={offer.grade}
              description={offer.description}
            ></List>))
            :
            <div id="dots1">
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </div>
          }
        </div>
      </div>
    </div>
  )
}

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.search
})

export default connect(mapStateToProps)(Search)