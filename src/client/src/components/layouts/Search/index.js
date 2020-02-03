import React, { useState, useEffect } from 'react'
import List from './List'
import a from './exampledata.json'

const data = a.offers;

export default () => {
  const [data, setData] = useState(undefined)

  // function getData(url){
  //   fetch(url)
  //   .then((response) => {
  //     return response.json();
  //   })
  //   .then((myJson) => {
  //     setData(myJson.offers);
  //   });
  // }

  useEffect(() => {
    setData(a.offers)
    //getData('http://192.168.99.100:5001/js');
  }, []);
  return (
    <div>
      <div className='content-box searchmain flexing search'>
        <label htmlFor="what">Czego szukasz: </label>
        <input id='what' type='text' />
        <label htmlFor="localization">Lokalizacja: </label>
        <input id='localization' type='text' />
        <button className="btn-blue">Szukaj</button>
      </div>
      <div className="searchmain flexing">
        <div className='content-box col25'>filtry</div>
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