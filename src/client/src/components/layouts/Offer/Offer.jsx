import React, { useState, useEffect } from 'react'

const DEFAULT_SELECT = '5'

export default props => {
  const [data, setData] = useState(undefined)
  const [isData, setIsData] = useState(true)
  const [gradeSent, setGradeSent] = useState(false)
  const [grade, setGrade] = useState(DEFAULT_SELECT)

  const getOffer = async url => {
    try {
      const response = await fetch(url)

      if (!response.ok) {
        throw new Error('getOffer response not ok')
      }

      const data = await response.json()
      setData(data)
    } catch (err) {
      setIsData(false)
      console.log(err)
    }
  }

  useEffect(() => {
    getOffer(`/api/offers/${props.match.params.id}`)
    // TODO get info about offer_rate
    // set gradeSent true if user already rated offer
  }, [])

  function goPayment() {
    console.log('tutaj bedzie sie płacić')
  }

  const rateOffer = async () => {
    try {
      const response = await fetch('/api/offer_rating', {
        method: 'POST',
        body: JSON.stringify({ grade, offerId: props.match.params.id }),
      })

      if (!response.ok) {
        throw new Error('rateOffer not ok')
      }

      setGradeSent(true)
    } catch (err) {
      console.log(err)
    }
  }

  return (
    <div className="content-box flexcolumn search searchmain">
      {!!isData ? (
        data ? (
          <div>
            <div
              className="content-box marginer title centering"
              onClick={() => console.log(props.id)}
            >
              Szczegóły oferty:
            </div>
            <div className="flexrow">
              <div className="">
                <img className="descpic" alt="" src={data.picture} />
                {!gradeSent ? (
                  <div>
                    <div className="centering"> rateOffer it!</div>
                    <div className="centering">
                      <select
                        id="grade"
                        defaultValue={DEFAULT_SELECT}
                        onChange={e => setGrade(e.target.value)}
                      >
                        <option value="5">5</option>
                        <option value="4">4</option>
                        <option value="3">3</option>
                        <option value="2">2</option>
                        <option value="1">1</option>
                      </select>
                      <span>★</span>
                    </div>
                    <button onClick={rateOffer} className="ratebutton">
                      Send
                    </button>
                  </div>
                ) : (
                  <div className="centering">Your grade was sent!</div>
                )}
              </div>
              <div className="col75">
                <div className="title centering">
                  <b>{data.name}</b>
                </div>
                <div className="flexrow flexjustify">
                  <div className="marginer price">{data.price} zł</div>
                  <div className="marginer">
                    {data.firstname + ' ' + data.lastname}
                  </div>
                  <div className="marginer">{data.day}</div>
                  <div className="marginer">{data.localization}</div>
                </div>
                <span className="description marginer">{data.description}</span>
              </div>
            </div>
            <button
              className="ratebutton paybutton"
              onClick={() => goPayment()}
            >
              Zapłać
            </button>
          </div>
        ) : (
          <div />
        )
      ) : (
        <div
          id={props.id}
          className="content-box marginer title centering"
          onClick={() => console.log(props.id)}
        >
          Sorry, this offer is no longer available
        </div>
      )}
    </div>
  )
}
